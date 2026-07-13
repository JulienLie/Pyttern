import sys
import os
import argparse
import yaml
from loguru import logger
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.language_processors.languages import Languages
from pyttern.language_processors import get_processor
from pyttern.visualizer.pda_visualizer import visualize_pda
from pyttern.visualizer.pt_visualizer import visualize_parse_tree
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

def main():
    parser = argparse.ArgumentParser(description="Pyttern Graph Tool: Visualize PDA structure or Parse Tree from code/patterns.")
    parser.add_argument("input", nargs="?", help="Input file (python or pyttern).")
    parser.add_argument("output", nargs="?", help="Output PDF filename.")
    parser.add_argument("--config", help="Path to a YAML configuration file for options.")
    parser.add_argument("--type", choices=['tpa', 'pt'], help="Type of visualization: tpa (PDA) or pt (Parse Tree).")
    parser.add_argument("--lang", choices=['python', 'java'], help="Language of the input file.")
    parser.add_argument("--wrap-at", type=int, help="Number of nodes before wrapping to a new line.")
    parser.add_argument("--nodes", help="Nodes to show (e.g., '0-10,20-30').")
    parser.add_argument("--font-size", type=int, help="Font size for labels.")
    parser.add_argument("-v", "--verbose", action="count", default=0, help="Increase verbosity.")

    args = parser.parse_args()

    # Default values
    options = {
        "input": args.input,
        "output": args.output if args.output else "visualization_output",
        "lang": "python",
        "type": "tpa",
        "wrap_at": None,
        "nodes": None,
        "highlight": None,
        "font_size": 14,
        "verbose": args.verbose
    }

    # Load from config file if provided
    if args.config:
        if not os.path.exists(args.config):
            print(f"Config file not found: {args.config}")
            sys.exit(1)
        try:
            with open(args.config, 'r') as f:
                config_data = yaml.safe_load(f)
                if config_data:
                    # Map the YAML fields to our options dictionary
                    options.update(config_data)
        except Exception as e:
            print(f"Error reading config file: {e}")
            sys.exit(1)

    # CLI arguments override everything else
    if args.input: options["input"] = args.input
    if args.output: options["output"] = args.output
    if args.lang: options["lang"] = args.lang
    if args.type: options["type"] = args.type.lower()
    if args.wrap_at is not None: options["wrap_at"] = args.wrap_at
    if args.nodes: options["nodes"] = args.nodes
    if args.font_size: options["font_size"] = args.font_size

    # Validation
    if not options["input"]:
        parser.error("the following arguments are required: input (or provide it in --config)")

    # Configure logger
    logger.remove()
    verbosity = options.get("verbose", 0)
    if verbosity == 0:
        logger.add(sys.stdout, level="INFO")
    elif verbosity == 1:
        logger.add(sys.stdout, level="DEBUG")
    else:
        logger.add(sys.stdout, level="TRACE")

    input_path = options["input"]
    if not os.path.exists(input_path):
        logger.error(f"Input file not found: {input_path}")
        sys.exit(1)

    output_pdf = options["output"]
    if output_pdf.endswith(".pdf"):
        output_pdf = output_pdf[:-4]

    try:
        processor = get_processor(Languages[options["lang"].upper()])
        tree = processor.generate_tree_from_file(input_path)

        viz_type = options.get("type", "tpa").lower()
        
        if viz_type == "pt":
            visualize_parse_tree(
                tree, 
                output_pdf, 
                title=f"Parse Tree - {os.path.basename(input_path)}", 
                font_size=options["font_size"],
                node_intervals=options.get("nodes"),
                highlights=options.get("highlight")
            )
            logger.info(f"Generated {output_pdf}.pdf (Parse Tree)")
        else:
            # Auto-load any subpatterns in the same directory
            input_dir = os.path.dirname(os.path.abspath(input_path))
            for file in os.listdir(input_dir):
                if file.endswith(".myt"):
                    try:
                        parse_subpattern_from_file(os.path.join(input_dir, file), Languages[options["lang"].upper()])
                        logger.info(f"Auto-loaded subpattern file: {file}")
                    except Exception as e:
                        logger.warning(f"Failed to auto-load subpattern {file}: {e}")

            pdas = processor.create_pda(tree)
            visualize_pda(
                pdas, 
                output_pdf, 
                title=f"PDA - {os.path.basename(input_path)}",
                wrap_at=options.get("wrap_at"),
                node_intervals=options.get("nodes"),
                highlights=options.get("highlight"),
                font_size=options["font_size"]
            )
            logger.info(f"Generated {output_pdf}.pdf (PDA with subpatterns)")
            
    except Exception as e:
        logger.exception(f"Error generating graph: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()

// bindings.cpp
#include <pybind11/pybind11.h>
#include <pybind11/stl.h>
#include "Node.h"
#include "TreeBuilder.h"
#include "Python3Lexer.h"

namespace py = pybind11;
using namespace py::literals;

std::shared_ptr<Node> parse_python_code(const std::string &code) {
    antlr4::ANTLRInputStream input(code + "\n");
    Python3Lexer lexer(&input);
    antlr4::CommonTokenStream tokens(&lexer);
    Python3Parser parser(&tokens);

    auto tree = parser.file_input();
    TreeBuilder builder;
    return std::any_cast<std::shared_ptr<Node>>(builder.visit(tree));
}

std::shared_ptr<Node> parse_python_subpattern(const std::string &code) {
    antlr4::ANTLRInputStream input(code + "\n");
    Python3Lexer lexer(&input);
    antlr4::CommonTokenStream tokens(&lexer);
    Python3Parser parser(&tokens);

    auto tree = parser.subpattern_input();
    TreeBuilder builder;
    return std::any_cast<std::shared_ptr<Node>>(builder.visit(tree));
}

PYBIND11_MODULE(_pyttern_cpp, m) {
    py::class_<Node, std::shared_ptr<Node>>(m, "Node")
        .def_readonly("rule_name", &Node::rule_name)
        .def_readonly("is_terminal", &Node::is_terminal)


        .def_readonly("children", &Node::children)
        .def_property_readonly("parentCtx", &Node::getParent)
        .def("getChildCount", &Node::getChildCount)
        .def("getChild", [](const Node &n, const int i, std::string rule_type) {
            if(rule_type.empty()) return n.getChild(i);
            return n.getChild(i, rule_type);
        }, py::arg("i"), py::arg("rule_type") = "")
        .def("getChildren", [](const Node &n) { return n.children; })
        .def("getText", &Node::getText)
        .def("__str__", &Node::getText)
        .def("__repr__", [](const Node &n) {
            return "<Node " + n.rule_name + ": '" + n.text + "'>";
        })
        .def("accept", [](const std::shared_ptr<Node> &self, py::object visitor) {
            std::string method_name = "visit" + self->rule_name;
            auto method = visitor.attr(*method_name);
            if(method) return method(self);
            return visitor.attr("visit")(self);
        });

    m.def("parse_code", &parse_python_code, "Parse and prune Python code in C++");
    m.def("parse_subpattern", &parse_python_subpattern, "Parse and prune Pyttern subpattern in C++");
}

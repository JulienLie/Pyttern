import shutil
import subprocess
import sys
from pathlib import Path


def build():
    root_dir = Path(__file__).parent.resolve()
    build_dir = root_dir / "build"
    build_dir.mkdir(exist_ok=True)

    # 1. Run CMake configure
    subprocess.check_call(
        [
            "cmake",
            "-B", str(build_dir),
            "-S", str(root_dir),
            f"-DPython3_EXECUTABLE={sys.executable}",
            "-DCMAKE_BUILD_TYPE=Release",
        ],
        cwd=root_dir,
    )

    # 2. Run CMake build
    subprocess.check_call(
        ["cmake", "--build", str(build_dir), "--config", "Release"],
        cwd=root_dir,
    )

    # 3. Copy compiled .so into pyttern/
    target_dir = root_dir / "pyttern"
    copied = False
    for so_file in build_dir.rglob("*_pyttern_cpp*.so"):
        shutil.copy2(so_file, target_dir)
        print(f"Copied {so_file.name} -> {target_dir}")
        copied = True

    if not copied:
        print("Warning: No _pyttern_cpp*.so found in build directory.")
    else:
        # 4. Generate/update type stubs if pybind11-stubgen is installed
        try:
            subprocess.run(
                [
                    sys.executable,
                    "-m",
                    "pybind11_stubgen",
                    "pyttern._pyttern_cpp",
                    "-o",
                    str(root_dir),
                    "--exit-code",
                ],
                cwd=root_dir,
                check=False,
            )
        except Exception as e:
            print(f"Notice: Stub generation skipped: {e}")


if __name__ == "__main__":
    build()


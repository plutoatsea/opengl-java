# Lightweight Java OpenGL Starter

**Before Use**: Install from https://www.lwjgl.org/customize Jar Packages, create a `lib/` folder and extract all Jar files into it.

```ps
javac -d bin -cp "lib/*" src\*.java 
java  --enable-native-access=ALL-UNNAMED  -cp "bin;lib/*" Main
```

### Example Tree:
```sh
.
├── README.md
├── bin
│   ├── DisplayManager.class
│   └── Main.class
├── lib
│   ├── lwjgl-assimp-natives-windows.jar
│   ├── lwjgl-assimp.jar
│   ├── lwjgl-glfw-natives-windows.jar
│   ├── lwjgl-glfw.jar
│   ├── lwjgl-natives-windows.jar
│   ├── lwjgl-openal-natives-windows.jar
│   ├── lwjgl-openal.jar
│   ├── lwjgl-opengl-natives-windows.jar
│   ├── lwjgl-opengl.jar
│   ├── lwjgl-stb-natives-windows.jar
│   ├── lwjgl-stb.jar
│   ├── lwjgl-unsafe.jar
│   └── lwjgl.jar
└── src
    ├── DisplayManager.java
    └── Main.java

4 directories, 19 files
```
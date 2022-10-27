# The Tic Tac Toe Project

-----------------------------------------------------------------------------------

## Build instructions

- Build distributions using Maven tool

```bash
mvn clean package
```

- Use following archives
  - `target/tic-tac-toe-${project.version}-windows.zip` for Windows
  - `target/tic-tac-toe-${project.version}-unix.tar.gz` for Unix


## Run instructions

- Download [OpenJDK 17](https://jdk.java.net/17/);
- Unzip the downloaded OpenJDK archive
- Configure the `PATH` environment variable
  - Add `%JDK_HOME%\bin\` directory for Windows;
  - Add `%JDK_HOME/bin/` directory for Unix;
- Re-Login or restart computer;
- Unzip he Tic Tac Toe distribution
    - Unzip `tic-tac-toe-${project.version}-windows.zip` for Windows;
    - Unzip `tic-tac-toe-${project.version}-unix.tar.gz` for MacOS and Linux;
- Go to unzipped directory;
- Run the game by double-click on the start script:
    - `start.cmd` for Windows;
    - `start.sh` for MacOS and Linux;

-----------------------------------------------------------------------------------

## Readme tutorial

- https://guides.github.com/features/mastering-markdown/
- https://help.github.com/categories/writing-on-github/

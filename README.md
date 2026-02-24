# password-store

A simple password safe for local purposes.


# Build

* mvn clean install


# Run

## Configuration

### Property `password.store.configuration.password-entries.file-name`

Set this property to the path and file name where you want to have your password data stored.

### Property `logging.level.root`

Best level for logging in productive environment is `WARN`.


## Start

Start the application with `java -jar code/gui-swing/target/password-store-gui-swing-1.1.0.jar` from the project directory.

First step is to input a password which is used to encode your data. So choose a strong password and keep it in mind (or somewhere else) for the next application start. **Loosing the password means loosing your data!!!**
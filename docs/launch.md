## Application launch
[back to README](./../README.md)

### Environment preparation
Create an `.env` file according to the template found in the `.env.template` file. You can copy the file and fill in the missing values. It is important that the file with the variables is named `.env` and that it is in the same folder as `docker-compose.yml`. Docker compose automatically retrieves the variables found in the `.env` configuration file.
```bash
cp .env.template .env
# fill in the values of the variables in the .env file
```

### Local activation of the application
To build and run the application go to the root directory and use the following command.
```bash
docker compose up --build -d
```
If you want to disable docker you should use the following command.
```bash
docker compose stop
```

### Initialization test data for the database
After setting docker with database, there is a possible to set default tree for tests. Just use script `mock-database.sh` that is located in `test` directory. Script can be executed from any directory.

```bash
# go to root directory and run
./test/mock-database.sh
```
[back to README](./../README.md)

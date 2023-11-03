# Treenity

## Introduction

Treenity is a tool created in the Java programming language with the aim of enabling users to create, edit, and manage hierarchical tree structures. This documentation describes the structure, functionalities, and how to use this application.

The application allows for the creation, editing, deletion, and storage of numerical data in tree nodes. Additionally, it automatically calculates sums of values along the paths leading to the root of the tree, facilitating data analysis. Details under the [link](./docs/task.md).

### Technologies used in the application 
* Java 17
  * Spring
  * Hibernate
* React 18
  * React flow
  * Dagre
* Postgres
* Docker

## Table of Contents
1. [Application launch](./docs/launch.md)
2. [Usage description](./docs/usage.md)

## Quick reference guide
```bash
# commands to be executed in the root directory
cp .env.template .env
# fill in the values of the variables in the .env file

docker compose up --build -d
./test/mock-database.sh
```



<h1 align="center" id="title">Simple CRUD MongoDB</h1>

## Table of Contents

* [About The Project](#about-the-project)
* [Prerequisites](#prerequisites)
* [Installation](#installation)

* ### About The Project

This is a simple CRUD (Create, Read, Update, Delete) application built with Spring Boot and MongoDB. It allows users to perform basic operations on a collection of data stored in a MongoDB database.

### Prerequisites

* Docker
* Java 21

  ### Installation

1. Change mongodb volume to your directory

```yaml
      volumes:
      - ./mongo-init.js:/docker-entrypoint-initdb.d/mongo-init.js:ro
      - <your-directory>:/data/db
      - ./mongo-entrypoint.sh:/usr/local/bin/mongo-entrypoint.sh
```

2. Run Docker

3. Build docker image and docker-compose up<br>

```sh
  docker-compose up -d --build
```

4. Run initial data script.

```sh
  docker exec -it mongodb /bin/bash mongo-entrypoint.sh
```

5. Restart app service.

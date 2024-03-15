db = db.getSiblingDB('admin');

if (db.getUser("springboot-mydatabase") == null) {
    db.createUser({
        user: "springboot-mydatabase",
        pwd: "password",
        roles: [
            {role: "readWrite", db: "mydatabase"},
            {role: "read", db: "admin"},
            {role: "read", db: "config"}
        ]
    });
}

db = db.getSiblingDB('mydatabase');

if (db.getUser("springboot-mydatabase") == null) {
    db.createUser({
        user: "springboot-mydatabase",
        pwd: "password",
        roles: [
            {role: "readWrite", db: "mydatabase"},
            {role: "read", db: "admin"},
            {role: "read", db: "config"}
        ]
    });
}

db.createCollection("author")

db.createCollection("book")

db.author.insertMany([
    {name: "Joanne Rowling", penName: "J. K. Rowling", site: "https://www.jkrowling.com", nationality: "British"},
    {name: "John Ronald Reuel Tolkien", penName: "J. R. R. Tolkien", nationality: "British"}
])

const jkRowling = db.author.findOne({penName: "J. K. Rowling"})._id;
const jrrTolkien = db.author.findOne({penName: "J. R. R. Tolkien"})._id;

db.book.insertMany([
    {title: "Harry Potter and the Sorcerer’s Stone", series: "Harry Potter", year: 1997, author: jkRowling},
    {title: "Harry Potter and the Chamber of Secrets", series: "Harry Potter", year: 1998, author: jkRowling},
    {title: "Harry Potter and the Prisoner of Azkaban", series: "Harry Potter", year: 1999, author: jkRowling},
    {title: "The Fellowship of the Ring", series: "The Lord of the Rings", year: 1954, author: jrrTolkien}
])
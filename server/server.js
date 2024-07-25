const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(bodyParser.json());

// In-memory data store
let users = [];
let books = [];

// Routes

// Create a user
app.post('/users', (req, res) => {
    const { name, email, status } = req.body;
    const newUser = { id: users.length + 1, name, email, status };
    users.push(newUser);
    res.status(201).json(newUser);
});

// Get all users
app.get('/users', (req, res) => {
    res.json(users);
});

// Delete a user
app.delete('/users/:userId', (req, res) => {
    const { userId } = req.params;
    users = users.filter(user => user.id !== parseInt(userId));
    res.status(204).end();
});

// Create a book for a user
app.post('/users/:userId/books', (req, res) => {
    const { userId } = req.params;
    const user = users.find(user => user.id === parseInt(userId));
    if (!user) {
        return res.status(404).json({ error: 'User not found' });
    }
    const { title, author } = req.body;
    const newBook = { id: books.length + 1, userId: parseInt(userId), title, author };
    books.push(newBook);
    res.status(201).json(newBook);
});

// Get all books for a user
app.get('/users/:userId/books', (req, res) => {
    const { userId } = req.params;
    const userBooks = books.filter(book => book.userId === parseInt(userId));
    res.json(userBooks);
});

// Delete a book for a user
app.delete('/users/:userId/books/:bookId', (req, res) => {
    const { userId, bookId } = req.params;
    books = books.filter(book => !(book.userId === parseInt(userId) && book.id === parseInt(bookId)));
    res.status(204).end();
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
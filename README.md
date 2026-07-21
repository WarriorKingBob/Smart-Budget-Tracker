# Introduction

Backend for the Smart Budget Tracker project.

In this budget tracker, a user can have multiple accounts belonging to same or different banks. When recording a transaction, it must belong to an account and a category. When a budget is created, it can belong to a overall budget, or in a specific category to be tracked.

This repository contains both the backend handling data between the PostgresSQL database, and the React frontend for web app.

# Design

The Postgres database currently runs locally in a Docker container.

Schema for the database:
![](Project%20ER%20Diagram.jpg)

Current progress:

Backend successfully implemented with appropriate REST API endpoints in Controller files.

Starting implementation of frontend with React + Vite. A basic transaction page has been successfully implemented.
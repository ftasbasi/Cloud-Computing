Middle East Technical University - 
Computer Engineering Department

(Furkan TAŞBAŞI)

CENG 495 - Cloud Computing Spring 2020-2021 Homework 2

In this assignment, I implemented a simple Game Distributor App using Javascript and MongoDB Realm.

(You need to refresh the page to see your action results)

Project Details:

index.html          =>  It's the main page which is connected to MongoDB Cluster via Realm framework.
                        In this page,
                                        CRUD operations are implemented both for users and games.
                                        Users have unique id "username".
                                        This page provides login operation for users (No authentication requirement is implemented for simplicity - No password)

user.html           =>  It's a page for useroperations. You need to login for accessing this page.
                        User is prohibited from commenting if he/she has not played respected game yet.
                        This page enables for operations as following:
                                                                        PLAYING, RATING, COMMENTING
                        This page provides some statistics for user as following:
                                                                        AVG RATING, MOST PLAYED GAME, TOTAL GAME PLAY FOR ALL
                        This page provides all comments done bycurrent user including information for respected game.

games.html          =>  It's a page for games (Games Dashboard).
                        This page provides statistics (play times,avg ratings, comments) and informations for all games.

styles/style.css    =>  Style sheet for html.


Just download files and run the index.html through your browser.

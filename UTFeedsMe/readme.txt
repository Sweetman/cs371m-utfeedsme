Members

Andre Peng - acp974
James Sweetman - jts2939
Daniel Haas - djh68


Instructions

The application will launch the Home screen and display 4 buttons: "Happening Now", "Near You", "All Events", and
"Add Event".  Happening Now will list all of the free food events that are currently happening,
according to the events' timeframes.  "Near You" will get your current location (which building 
you're in) and determine which events will happen in that same buildling.  All Events will simply
list out all events.  Add Event will add a new event to the list of events.  


Features completed for Alpha Release

The SQLite database has been set up and is working fine in the application.  Pressing the Add Event
button will add a mock-up event into the database, then display all of the events added thus far.
Happening Now takes the current time and displays which events are currently happening, based
on their time frames.  Near You will just display all events located in GDC for this alpha release.


Features not completed for Alpha Release

There are still many more features to add, but those will be added on Beta or future releases.
These features include allowing the user to submit an event they define themselves, karma associated
with users (based on Reddit), shadowbanning of low-karma users, push notifications to notify users
of upcoming events nearby, a map to pinpoint the event locations, expansion to other campuses, 
and a color code for the different types of organizations that can host the events.


Features included that aren't in the prototype

None.  Everything implemented thus far is specified in the design doc.


Classes from outside:
- MySQLiteHelper (http://www.vogella.com/tutorials/AndroidSQLite/article.html)
- Record (http://www.vogella.com/tutorials/AndroidSQLite/article.html)
- RecordsDataSource (http://www.vogella.com/tutorials/AndroidSQLite/article.html)


Classes we wrote ourselves:
- AddEvent
- AllEvents
- HappeningNow
- NearYou
- StartScreen
# Barking
### A typo? We will never know..
Android Project (Parking Reservation App)

# Opening Screen
A simple fragmented activity, with a log in and sign up option.

# Home Screen
After logging in, the user is taken to the Reservation Activity, where they enter a date and timeslot, after which they proceed to choosing the city in which they wish to park.
A slightly altered layout is shown if the user changes the orientation of the device to landscape.

# Cities
Once the user has chosen their preferred date and timeslot, they pick the city.

# Parkings
As an alpha version, each city contains either one or two parkings as of yet (one in the center, and an optional outskirts parking).
The available spaces are dynamically calculated by checking the number of reservations in the database for that particular city.

# Reservation Confirmation
The reservation is sent into the database under the user's name.
The user is taken to a Confirmation screen, where they are presented with information concerning the reservation itself (parking location, date & time). A QR code is generated from the reservation ID (a randomly generated number between 0 and 1000).
They have the option to view it with the click of the presented button, immediately after which they are redirected to another activity.
If changes the orientation of the device to landscape, the QR code is shown beside the reservation info.

# Navigation
When the user clicks the Navigate button, the coordinates stored in the database for the particular parking lot are taken and used to begin a driving navigation via Google Maps. At this alpha stage of the app, both the cities and the parking lots share the same coordinates.

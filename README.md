# RandomPersonApp
Using Kotlin to create an app that utilizes random.me api to generate a random persons information, good for making template websites/portfolios as the information acts as a Lorem Ipsum

# File Description
There are 5 main files: Main Activity, User, UserApiService, UserProfileScreen, and UserViewModel.

# User View Model
Utilizing Kotlins View Model, which creates a UI state holder for the app, we initialize the function on load to generate a person before the user clicks anything. We update loading and error variables. We also make sure to use FirstOrNull in case of an error, sending a null user to avoid crashing the app functionality.

# User Profile Screen
This is the main app screen. We use modified columns to fill the screen, and give the user a circular loading state if the loading variable is true. When the user information is fetched, we disply it on this screen, along with a button that calls our view model again to reload new information.

# User Api Service
We use retrofit as a safe https fetcher for api data. We then use a gson to get our data and build an object to use for our app of the persons information. All of this is stored using the Kotlin lazy functionality that only grabs information when it is called on by the app, preventing the api from being contantly called upon.

# User
Pretty simple, we just store the user information in varies variables based on the json block list name.

# Main Activity
Calls the User Profile Screen when the app is "created" aka loaded.

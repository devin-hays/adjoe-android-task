# Adjoe Android Task
Adjoe Android Developer Coding Task

## Task
### Java android is your friend - 2h task
Please install on your computer: Android studio and an Android emulator.        
Please write Java code (no Kotlin).        
The app should be able to run on every device from Android 5.0 on.      

### Part 1. Native Android
- 1.1 Create an activity which loads data from this URL https://jsonplaceholder.typicode.com/albums
(Please do not use libraries like Retrofit, OkHttp or GSON for the network code, only classes from the java.*, android.* or org.json.* packages.)
- 1.2 Find a way to render the album titles sorted by ID into a view, which is scrollable (even if they are already sorted) . You can use libraries like RecyclerView for this.
  
### Part 2. Services
- 2.1 Build a BroadcastReceiver that listens to the Intent with the action . Please note: there is no need to create an Intent with action , please use the existing one.
- 2.2 Implement a background task (for example: JobScheduler or background service) that runs periodically
- 2.3 Every time the background task runs, create a push notification that shows the user how long the phone was active since the Intent was triggered (the time should be updated every few seconds)

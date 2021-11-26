# SubHub
#### The right way to subscribe


## Hey You!

*Have **you** ever accidentally paid $99.99 for that free trial you **forgot** to cancel?*

*Do **you** find yourself **forgetting** all about those subscriptions to CatTube and Legends in Leagues that you 
purchased eons ago?*

Well I've got just the app for you! 

Introducing **SubHub**, the all-in-one Subscription Management application perfect for you, your business, or even 
Grandma! 

SubHub lets you:

- Keep track of your subscriptions in one place 
- View your current subscriptions
- Check when they renew
- Manage and cancel your subscriptions 

 
I made this application because I too, have overpaid for services I forgot I registered for, 
or only wanted to use for a short period of time. SubHub is here to make sure you never get charged for those 
free trials or unwanted products **ever** again.

  
   
### User Stories

1. As a user, I want to add a new subscription to a list of my subscriptions.
2. As a user, I want to view a list of all my subscriptions. 
3. As a user, I want to find out when my subscription will renew.
4. As a user, I want to be able to cancel or remove a subscription from my list. 
5. As a user, I want to be able to save my subscription information to file while 
using the application and when quitting it.
6. As a user, I want to have the option to reload data from the file when I start the 
application.


### Phase 4: Task 2

The EventLog class created a record of the events that occur during runtime, 
and outputs them to the console. A sample of this EventLog is included below.


Event Log:

Fri Nov 26 08:04:05 PST 2021
Subscriptions from file added to list!

Fri Nov 26 08:04:20 PST 2021
New subscription added to list!

Fri Nov 26 08:04:37 PST 2021
New subscription added to list!

Fri Nov 26 08:04:50 PST 2021
Subscription was removed from list!


### Phase 4: Task 3

One way the code can be refactored is to follow along with the Singleton Pattern. 
- This would be applied to the ListOfSubscription class.
- This would result in the code creating a single, globally accessible list of subscriptions
which would reduce the duplication of declarations in other classes that utilize the list. 
- As such, there would be reduced risk of incorrect references and ensure that all subscriptions get added to the same 
list.

Another refactoring can be applied to the subscription class, with how the Renewal Period field is handled.
- When adding a new subscription, the Renewal Period would be presented in the form of
3 radio buttons, each with a corresponding period type. 
- This would reduce the likeliness of user error and eliminate the need for current guards against invalid numerical input that are currently in the code.

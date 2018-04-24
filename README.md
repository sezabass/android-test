# android-test

Hi, this is and Android Test project designed to show common usages of some 
of the most well-known Android libraries out there, such as:
- Retrofit (with Jackson)
- Dagger
- Robolectric
- Picasso
- Espresso

This project is about reading some input from the [Reddit API](https://www.reddit.com/dev/api/)
and showing the results to the user.  
In addition to that, if a user should tap on one of the items, he/she should
see its details, a list of its comments and be able to open the item's url
on a webview.

###### Known bugs
- Comments list is not loading properly because of a deserialization problem.

###### Some missing features I would still like to implement on this:
- RxJava
- Animations and Transitions
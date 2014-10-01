JBugmenot
=========

Java [Bugmenot](http://www.bugmenot.com/) library to find account and password for various sites.


Install
-------

If you use Maven to manage the dependencies in your Java project (and you should!), you do not need to download; just place the following into your POM's <dependencies> section:
```xml
<dependency>
  <!-- Bugmenot library to find account and password for various sites -->
  <groupId>com.github.davidepastore</groupId>
  <artifactId>jbugmenot</artifactId>
  <version>0.1.0</version>
</dependency>
```

Usage
-----

Find all the accounts of a site:

```java
ArrayList<Account> accounts = JBugMeNot.getAllAccounts("nypost.com");
```

Find the first username/password of a site:

```java
ArrayList<Account> accounts = JBugMeNot.getAllAccounts("imdb.com");
Account firstAccount = accounts.get(0);
String username = firstAccount.getUsername();
String password = firstAccount.getPassword();
long votes = firstAccount.getVotes();
```

Find all the accounts that have a given succes rate:

```java
JBugmenot.setMinimumSuccessRate(50);
ArrayList<Account> accounts = JBugmenot.getAllAccounts("nypost.com");
```

Issues
------

If you find problems, please open an issue [here](https://github.com/DavidePastore/JBugmenot/issues).

JBugmenot
=========
[![Build Status](https://travis-ci.org/DavidePastore/JBugmenot.svg?branch=master)](https://travis-ci.org/DavidePastore/JBugmenot)

Java [Bugmenot](http://www.bugmenot.com/) library to find account and password for various sites.


Install
-------

If you use Maven to manage the dependencies in your Java project (and you should!), you do not need to download; just place the following into your POM's <dependencies> section:
```xml
<dependency>
  <!-- Bugmenot library to find account and password for various sites -->
  <groupId>com.github.davidepastore</groupId>
  <artifactId>jbugmenot</artifactId>
  <version>0.2.0</version>
</dependency>
```

Usage
-----

Find all the accounts of a site:

```java
ArrayList<Account> accounts = JBugMeNot.getAllAccounts("nypost.com");
```

Find the first login of a site:

```java
ArrayList<Account> accounts = JBugMeNot.getAllAccounts("imdb.com");
Account firstAccount = accounts.get(0);
String username = firstAccount.getUsername();
String password = firstAccount.getPassword();
long votes = firstAccount.getVotes();
Date addingDate = firstAccount.getAddingDate();
```

Find all the accounts that have a given succes rate:

```java
JBugmenot.setMinimumSuccessRate(50);
ArrayList<Account> accounts = JBugmenot.getAllAccounts("nypost.com");
```

Vote an account:

```java
ArrayList<Account> accounts = JBugmenot.getAllAccounts("corriere.it");
Account lastAccount = accounts.get(accounts.size() - 1);
JBugmenot.vote(lastAccount, false);
//OR
lastAccount.vote(false);
```

Issues
------

If you find problems, please open an issue [here](https://github.com/DavidePastore/JBugmenot/issues).

JBugmenot
=========

Bugmenot library for Java to find account and password for various sites.


Usage
-----

Find all the accounts of a site:

    ArrayList<Account> accounts = JBugMeNot.getAllAccounts("nypost.com");

Find the first username/password of a site:

    ArrayList<Account> accounts = JBugMeNot.getAllAccounts("imdb.com");
    Account firstAccount = accounts.get(0);
    String username = firstAccount.getUsername();
    String password = firstAccount.getPassword();


Issues
------

If you find problems, please open an issue [here](https://github.com/DavidePastore/JBugmenot/issues).

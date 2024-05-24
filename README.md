# SeleniumBooking
# **About this repository**
This repository contains code sample using selenium4 with  Maven , testNG and POM design pattern 

  # **Environment :-** 


    # JAVA ===> java 17
    # selenium 4 ===> automate webdriver
    # TestNG ===>  test runner and annotations provider 
    # Maven ===> build and mangement tool with its pom.xml file 
    # IntelliJ ===> IDE
    # version control ===> git
    # repository control && pipeline configurations ===>github
    # report ===> ExtentReport , surefire report into target once running from terminal
    # read data from ===> excel sheet
    
  
  
  
  # **Runner** :- 
  
     # CMD ====>  mvn clean verify   from the level of project 
     # or we can run Testng xml file (booking.xml)
     # or the regression class from test/regression package
    

  # **Target** :- 
  
     # Create a  selenium4 framework to accomplish the following. 
     # a. Open https://www.booking.com
     # b. search for alexandria and select check in and check out dates
     # c. click search and check tolip displayed 
     # d. select visit from Tolip
     # e. select number of rooms and bed type 
     # f. validate that tolip displayed int reservation card 

           
           
   # **Note** :- 
     
     # in case of java version != 17 we can change the default SDK for projetcs modules to the current installed java version as following : 
          1- Open File menu 
          2- Open Project Structure 
          3- Choose from the dropdown menu the available java version 
          4- Click Apply then OK 
          After that you might need to restart the IDE
  # **challenges** :-
     # check on latest date into calnder view to click next month arrow if provided date not displayed
     # select visit from tolip Hotel
     # deal with login popup randomly displayed within the run


     
  





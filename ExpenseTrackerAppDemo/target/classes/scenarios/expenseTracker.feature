@expense 
Feature: Expense Tracker Test 

@expenseTracker 
Scenario Outline: Login, add an expense and Logout 
	Given I login to Expense Tracker app with username "<email>" and password "<password>" 
	And I add expense with head "<Head>" , amount "<Amount>" , "<Currency>" currency and category "<Category>" 
	And i attach receipt 
	Then I logout of application 
	
	Examples: 
		| email | password | Head | Amount | Currency | Category |
		| test@perfecto.com | test123 | Flight | 3000 | USD | Personal |
		
		
		
		

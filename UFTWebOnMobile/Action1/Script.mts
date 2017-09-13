SetupMobile()

url = "https://verizonwireless.com/smartphones"
Browser("title:=.*", "CreationTime:=0").Navigate url
Sync()

CompleteRunTimerStart = Timer()
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").Link("text:=Samsung Galaxy S7 edge", "html tag:=A").Click

Browser("Samsung Galaxy S7 edge").Page("Samsung Galaxy S7 edge_2").WebElement("WebElement").Click
Browser("Samsung Galaxy S7 edge").Page("Samsung Galaxy S7 edge_2").WebButton("Add to Cart").Click 
Browser("Samsung Galaxy S7 edge").Page("Samsung Galaxy S7 edge_2").WebButton("Get started").Click
Browser("Smartphones | Verizon").Page("Page").WebElement("WebElement").Click
Browser("Samsung Galaxy S7 edge").Page("Page").WebButton("Continue").Click
wait 2
Browser("Samsung Galaxy S7 edge").Page("Cart Page - Verizon Wireless").WebButton("Continue to Shopping Cart").Click
Browser("Samsung Galaxy S7 edge").Page("Cart Page - Verizon Wireless").Link("Check out").Click

pageSynced=false
wait 10
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").WebEdit("type:=text", "name:=checkoutFirstName", "html tag:=INPUT").Set "Philipp"
pageSynced=true
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").WebEdit("type:=text", "name:=checkoutLastName", "html tag:=INPUT").Set "Schwarze"
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").WebEdit("type:=text", "name:=checkoutAddress", "html tag:=INPUT").Set "PerfectoStreet 22"
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").WebEdit("type:=text", "name:=checkoutCity", "html tag:=INPUT").Set "Woburn"
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").WebEdit("type:=email", "name:=checkoutEmail", "html tag:=INPUT").Set "phil@perfectomobile.com"
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").WebEdit("type:=tel", "name:=checkoutPhone", "html tag:=INPUT").Set "(412) 486-5554"
Browser("title:=.*", "CreationTime:=0").Page("title:=.*").WebButton("type:=submit", "name:=Next Step", "html tag:=INPUT").Click
pageSynced=false

CompleteRunTimerEnd = Timer()
print "############# Time taken for complete Test: " & FormatNumber(CompleteRunTimerEnd - CompleteRunTimerStart, 2)

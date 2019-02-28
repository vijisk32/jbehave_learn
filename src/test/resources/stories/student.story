Meta: University Student

Narrative:
As a student
I want to know me average
So that to have a good mark


Scenario: The average must be 5

Given The student has a mark 4
When He has a mark 6
Then His average is 5
And The student has no misktake

Scenario: The average must be 6

Given The student has a mark 7
When He has a mark 5
Then His average is 6
Then He has 2 marks
And The student has no misktake

Scenario: The average must be 8

Given The student has a mark 7
When He has a mark 8
Then His average is 6
Then He has 2 marks
And The student has no misktake
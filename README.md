# NetComputing
System for effective garbage handling. RUG Net computing 2017-2018

Uses sensor data in garbage cans in order to determine the status of the can.
A user can request an overview of the garbage cans via a REST server.

### cmd Reminders ###
**Obtaining files:**  
git clone -b develop https://github.com/SofieLovdal/NetComputing.git  
git pull origin develop  

**Creating a new local branch:**  
git checkout -b newBranch develop  
note: must push origin branchName  

**Before a push:**  
git status  
git add folderName/* (or: git add -A)  
(remove file:) git rm fileName  
git commit -m "I just did some change"  
git push origin branchName  

**Add tag:**  
git tag -a tagName hashofCommit -m "tagging message"  
git push --follow-tags  

**Delete tag:**  
git tag -d tagName  
git push --delete origin tagName  

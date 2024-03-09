# *Welcome to Group-37 GitLab Repository*

## 1. Repository Rules
```bash
git status
```
if any changes are detected use:
```bash
git pull origin main
or 
git pull
```
## 2. Installation Guide
Follow these steps to install and run the project on your local machine.
* When the repository has been cloned do create your own branches to avoid **MERGE CONFLICT**
* Always Check for any recent changes by using

### Step 1: Clone the Repository
* Clone the repository via: 
```bash
git clone https://campus.cs.le.ac.uk/gitlab/co2201-2024/group-37.git
``````
* Navigate to the repository on your machine:
```bash
cd group-37
```
### Step 2: To change anything in the repository
* create a local branch using:
```bash
git switch -c your-branch-name
```
* After working on your local branch push it to the main branch:
```bash
git add .
git commit -a -m "Commit Message"
git push --set-upstream origin your-branch-name
```

* After Setting **upstream** to the origin branch You can use this as normal:
```bash
git push origin your-branch-name
```

* Now Youll need to Merge the Commit from your local branch to the main branch:

* If you go to **Merge Request** you can use it to merge the commit

### Step 3: Run the application

* If there is an error in creating the database or aws doesnt work try:
```sql
- Create a Mysql instance and create a local instance with
- user: c02103
- password: password
and create a database called co2103db
- Re-run the application now it should work
```

## Prerequisites
## **Tech stack Used:**
 [![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/) [![Gradle](https://img.shields.io/badge/Gradle-%2302303A.svg?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/) [![Spring](https://img.shields.io/badge/Spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
 [![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/HTML) [![CSS](https://img.shields.io/badge/CSS-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/CSS) 
 [![MySQL](https://img.shields.io/badge/MySQL-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
 [![AJAX](https://img.shields.io/badge/AJAX-%23F7DF1E.svg?style=for-the-badge&logo=ajax&logoColor=black)](https://developer.mozilla.org/en-US/docs/Web/Guide/AJAX)
 [![Bootstrap](https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com/)
 [![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)](https://aws.amazon.com/) 
 [![Railway Logo](https://img.shields.io/badge/Railway-%23669966.svg?style=for-the-badge&logo=railway&logoColor=white)](https://railway.app/)



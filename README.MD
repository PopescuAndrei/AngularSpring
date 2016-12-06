## Semantic Web Project

The project uses:

1. JDK 1.8
2. Maven
3. Hibernate
4. Spring Data Mongo
5. Spring MVC.

===
### Dependencies
1. This is a maven project, so in order to be run it needs an instance of maven running so it could download the dependencies.
2. mongo should be running in order for the application to run.
3. In mongo shell create database Projects3
> use Projects3

4. Create collection users and populate it with initial data 

    ```
    db.users.insert({'id':'1', 'content':'<users><user><id>sf48f0sd8</id><username>Andrei</username><role>director</role><mail>andrei.popescu93@gmail.com</mail><password>samplepass</password><first-name>Andrei</first-name><last-name>Popescu</last-name></user><user><id>ssdfsaf8</id><username>Vlad</username><role>proposer</role><mail>vlad.popescu93@gmail.com</mail><password>samplepass</password><first-name>Vlad</first-name><last-name>Popescu</last-name></user><user><id>ssdfsdafaaf8</id><username>Oana</username><role>proposer</role><mail>ionel92@yahoo.com</mail><password>samplepass</password><first-name>Oana</first-name><last-name>Ionel</last-name></user><user><id>3fasat28904</id><username>Alex</username><role>director</role><mail>alex93@gmail.com</mail><password>test123</password><first-name>John</first-name><last-name>Snow</last-name></user></users>', 'fileName':'users'})
    ```

5. Create collection projects and populate it with initial data

    ```
    db.projects.insert({'id':'2','content':'<projects><project><id>1sa22hkj121</id><title>Artas Project</title><budget>50000</budget><duration>100</duration><partners><partner><name>Alexander Kovaci</name><leader>true</leader></partner><partner><name>Andrew Mikalson</name><leader>false</leader></partner></partners><domain>Research and Development</domain><objective>Interchangeable mobile components for improved mobile phone performance</objective><stages><stage><name>Stage 1</name><duration>20</duration><description>Market Research</description></stage><stage><name>Stage 2</name><duration>60</duration><description>Application Development</description></stage><stage><name>Stage 3</name><duration>20</duration><description>Application Testing</description></stage></stages></project><project><id>12e32hnbhj1</id><title>Linux X Distribution</title><budget>200000</budget><duration>140</duration><partners><partner><name>Linus Torvald</name><leader>true</leader></partner><partner><name>Andrew Tanenbaum</name><leader>false</leader></partner><partner><name>Richard Stallman</name><leader>false</leader></partner></partners><domain>IT Open Source</domain><objective>New Linux distribution system compatible with windows applications</objective><stages><stage><name>Stage 1</name><duration>10</duration><description>Market Research</description></stage><stage><name>Stage 2</name><duration>50</duration><description>Architecture Development</description></stage><stage><name>Stage Test 3</name><duration>10</duration><description>Waterfall Testing</description></stage><stage><name>Stage Test 4</name><duration>10</duration><description>Mock up presentation</description></stage><stage><name>Stage Test 5</name><duration>20</duration><description>Functionality development</description></stage><stage><name>Stage Test 6</name><duration>20</duration><description>Testing</description></stage><stage><name>Stage Test 7</name><duration>20</duration><description>Application Launch</description></stage></stages></project><project><id>178nn32v432kq</id><title>Apollo Solaris</title><budget>100000000</budget><duration>300</duration><partners><partner><name>Charlie Bolden</name><leader>true</leader></partner><partner><name>Bill Gates</name><leader>false</leader></partner></partners><domain>Space</domain><objective>First self-sustaining space platform</objective><stages></stages></project><project><id>842a22hkj121</id><title>Project ARA</title><budget>5000</budget><duration>120</duration><partners><partner><name>Tia Gunder</name><leader>true</leader></partner><partner><name>Andrew Mikalson</name><leader>false</leader></partner><partner><name>Mike Ross</name><leader>false</leader></partner><partner><name>Victor Plikn</name><leader>false</leader></partner></partners><domain>Research and Development</domain><objective>Interchangeable mobile components for improved mobile phone performance</objective><stages><stage><name>Stage 1</name><duration>20</duration><description>Market Research</description></stage><stage><name>Stage 2</name><duration>60</duration><description>Application Development</description></stage><stage><name>Stage 3</name><duration>20</duration><description>Application Testing</description></stage><stage><name>Stage 4</name><duration>20</duration><description>Application Testing</description></stage><stage><name>Stage 5</name><duration>20</duration><description>Presentation</description></stage></stages></project><project><id>1124nn32v432kq</id><title>Politehnica</title><budget>10000</budget><duration>100000</duration><partners><partner><name>Andrei Popescu</name><leader>true</leader></partner><partner><name>Vlad Popescu</name><leader>false</leader></partner></partners><domain>Science Fiction</domain><objective>Better World</objective><stages><stage><name>Stage 1</name><duration>20</duration><description>Student Research</description></stage><stage><name>Stage 2</name><duration>60</duration><description>Professor Development</description></stage><stage><name>Stage 3</name><duration>20</duration><description>Session Testing</description></stage><stage><name>Stage 4</name><duration>20</duration><description>Failed Exam Testing</description></stage><stage><name>Stage 5</name><duration>20</duration><description>Se You Next Year</description></stage></stages></project><project><id>cca27b6a-b372-4550-9189-27490bde1961</id><title>Semantic Web</title><budget>10</budget><duration>9999</duration><partners><partner><name>Vlad Popescu</name><leader>false</leader></partner><partner><name>Vlad Popescu</name><leader>false</leader></partner></partners><domain>Computer Science</domain><objective>Learning</objective><stages><stage><name>Learning</name><duration>9999</duration><description>Tired</description></stage></stages></project><project><id>c5914064-5081-4aff-bf4e-bc61b07549df</id><title>Diploma Project</title><budget>10</budget><duration>365</duration><partners><partner><name>Andrei Popescu</name><leader>true</leader></partner></partners><domain>Education</domain><objective>Graduation</objective><stages><stage><name>Design</name><duration>100</duration><description>Design the application arhitecture</description></stage></stages></project><project><id>76085e9f-78ee-40d9-a85a-3a0c4386cce7</id><title>PDFandXML</title><budget>20</budget><duration>20</duration><partners><partner><name>Andrei Popescu</name><leader>true</leader></partner></partners><domain>Education</domain><objective>Test PDF and XML Downloads</objective><stages><stage><name>Final</name><duration>20</duration><description>Almost finished</description></stage></stages></project></projects>','fileName':'projects'})
    ```

6. In <addr>ro.fils.angularspring.util.ConnectionUtils.java</addr> there are 2 strings. modify the string with your id's in the database for the two collections. Mongo does them differently based on the machine it's running so this needs to be done.

7. Clean -> Build -> Run

    ```
    for director login
Mail: andrei.popescu93@gmail.com
Pass: samplepass
    ```

===
#### Enjoy! Feel free to fork and do whatever you want with it.

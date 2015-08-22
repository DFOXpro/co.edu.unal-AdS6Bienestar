#Cliente Web para co.edu.unal-AdS6Bienestar

##Fixing some ubuntu errors

For grunt watch problem http://stackoverflow.com/questions/16748737/grunt-watch-error-waiting-fatal-error-watch-enospc
```
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
```

For problem with newest node http://stackoverflow.com/questions/19953216/grunt-doesnt-give-any-output-ubuntu
```
sudo apt-get install nodejs-legacy
```

##Compiling the project
```
cd <this folder>
sudo npm install -g grunt-cli bower
npm install
bower install
grunt;#For dev
```

the output files is in <This folder>./build/
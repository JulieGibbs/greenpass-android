grep -m 1 "versionName" app/build.gradle.kts | awk '{print $3}' | sed 's/"//g'

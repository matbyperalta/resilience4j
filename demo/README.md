# Api rest spring boot
endpoint: GET http://localhost:8080/demo-api/test
description: implementa el circuit braker y cliente feign con resilience4j, las configuraciones son:
failureRateThreshold = 10
slowCallRateThreshold = 10
waitDurationInOpenState = 1 second
slowCallDurationThreshold = 2 seconds
permittedNumberOfCallsInHalfOpenState = 3
minimumNumberOfCalls = 10
slidingWindowType = COUNT_BASED
slidingWindowSize = 5
# Compile Command
./gradlew buils

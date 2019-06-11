# spring_boot_test_task

curl --request POST \
  --url http://localhost:8080/oauth/token/ \
  --header 'authorization: Basic dXNlcjpkZXZnbGFuLXNlY3JldA==' \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data username=user \
  --data password=password \
  --data grant_type=password
  
  curl --request GET \
  --url http://localhost:8080/api/products/ \
  --header 'authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjAyNjAyMjcsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiOWE0Yzc4MGYtZjFhYi00N2ZiLWFkYjItMzE4YWNmMGQ3NDQwIiwiY2xpZW50X2lkIjoidXNlciIsInNjb3BlIjpbIlJFQUQiLCJXUklURSJdfQ.JaVWtDCJfwtFRlOS9ElkxG0RFclxN3rbt_p-SnjthII'

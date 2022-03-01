# DemoLoginSimple
1. face data
2. write data get list 
3. search size , category , band , name 
4. mapping Entity --> DTO (example : entity have 10 fields --> return reponse is 5 fields) appply for all API 

# Note for @Service and @Controller
+ @Service should provide most of the business logic and process it.
+ @Controller must inject @Service to call method, run and return 'view'.
+ Log messages and catch Exceptions should be in @Service, @Controller should return error view when exceptions are detected. 

# Note for type of mapping in Controller
+ @GetMapping, @PostMapping correspond with Read and Create in CRUD
+ @PutMapping perform Update in CRUD
+ @DeleteMapping is Delete action in CRUD
+ @PatchMapping same with @PutMapping but use when you just need to update partially of the resource(like you just need to update password or email of an account)
+ @OptionsMapping .....

# MercadoExample

## How to get MercadoPago API key
1. From [Developer Panel](https://www.mercadopago.com.ar/developers/panel/) get your
application number and set up ANY redirect-url in application's options
2. Enter these fields into this link: `https://auth.mercadopago.com.ar/authorization?client_id={APPLICATION_ID}&response_type=code&platform_id=mp&state={RANDOM_ID}&redirect_url={REDIRECT_URL}`
and authorize through it
3. After authorization you will be redirected and in the link there will be a code in format `TG-XXX...`
4. Get application's secret in the "Credentials" page
5. Make a POST request to `https://api.mercadopago.com/oauth/token` with body similar to
```   
{  
   "client_id": APPLICATION_ID,  
   "client_secret" : APPLICATION_SECRET,  
   "grant_type": "authorization_code",  
   "code": "TG-XXXXX...",  
   "redirect_uri": REDIRECT_URL,  
   "test_token": false  
}
```
6. Set `mercadoApiKey`, `mercadoRefreshToken` and `mercadoUserID` in `gradle.properties`


[Video](https://youtu.be/I0yR0awzo0A)

## Dependency Injection (Dagger)
Dagger is used for Dependency Injection pattern, the modules' descriptions are provided below

### Retrofit
Used for API calls

### Remote
Contains details of MercadoPago API for Retrofit, all dataclasses, as well as facade DI module

# MercadoExample

## What's this?
This Android app is an example on how to work with MercadoPago's API with client and nothing else.
Please, do not use this in production. A lot of the implemented features are unsecure if ran on client and belong on the server-side.

## How to get MercadoPago API key and what to do with it
1. From [Developer Panel](https://www.mercadopago.com.ar/developers/panel/) get your
`application number` and set up `redirect-url` in application's options (can be any URL, if you don't have one)
2. Enter these fields into this link: `https://auth.mercadopago.com.ar/authorization?client_id={APPLICATION_ID}&response_type=code&platform_id=mp&state={RANDOM_ID}&redirect_url={REDIRECT_URL}`
and authorize through it
3. After authorization you will be redirected and in the page's url there will be a code in format `TG-XXX...`
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
6. Set `mercadoApiKey`, `mercadoRefreshToken` and `mercadoUserID` inside the `gradle.properties` file in the root directory


[Video](https://youtu.be/I0yR0awzo0A)

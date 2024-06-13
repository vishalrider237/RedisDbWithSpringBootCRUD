```toml
name = 'Post'
method = 'POST'
url = 'http://localhost:8080/users/create'
sortWeight = 2000000
id = '98ecb46d-174b-4ade-8583-c42fbd6512b2'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "name": "Vickey Pandey",
  "email": "vickey.pandey@gmail.com",
  "phone": "89766787889"
}'''
```

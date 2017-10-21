import socket                               # Import socket module

import hmac
import hashlib


s = socket.socket()                         # Create a socket object
host = socket.gethostname()                 # Get local machine name
port = 12345                                # Reserve a port for your service.

s.bind(('', port))                          # Bind to the port
f = open('New.txt','wb')
s.listen(5)                                 # Now wait for client connection.
while True:
    c, addr = s.accept()                    # Establish connection with client.
    print 'Got connection from', addr
    print "Receiving..."
    l = c.recv(1024)
    print l
    while (l):
        print "Receiving..."
        f.write(l)
        l = c.recv(1024)
    f.close()
    print "Done Receiving"
    c.send('Thank you for connecting')
    c.close()                               # Close the connection
    break
secretKey = raw_input("Enter the secret key: ")
#digest_maker = hmac.new(secretKey, , hashlib.sha1)

passedHash = raw_input("Enter the Hash value: ")
msg = raw_input("Enter the message to check the validity: ")
digest_maker = hmac.new(secretKey, msg, hashlib.sha1)


digest = digest_maker.hexdigest()
print "Hash: ", digest


if(digest == passedHash):
        print 'Passed'
else:
        print 'Failed'


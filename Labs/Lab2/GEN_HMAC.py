import hmac
import hashlib
import socket
secretKey = raw_input("Enter the secret key: ")

msg = raw_input("Enter the message: ")
digest_maker = hmac.new(secretKey, msg, hashlib.sha1)
digest = digest_maker.hexdigest()

print "Hash: ", digest

s = socket.socket()                         # Create a socket object
host = socket.gethostname()                 # Get local machine name
port = 12345                                # Reserve a port for your service.

s.connect(('', port))


#msg = raw_input("Enter the send Message: ")
#hash = raw_input("Enter the hash: ")
l = " Msg : "+  msg + " \n hash :" + digest
print 'Sending...'
s.send(l)



print "Done Sending"
s.shutdown(socket.SHUT_WR)
print s.recv(1024)
s.close                                     # Close the socket when done



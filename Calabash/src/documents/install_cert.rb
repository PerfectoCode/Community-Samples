require 'net/http'
  
# create a path to the file PerfectoMobileCA.pem
cacert_file = File.join([Dir.pwd, 'PerfectoMobileCA.pem'])
 
Net::HTTP.start("curl.haxx.se") do |http|
  resp = http.get("/ca/cacert.pem")
  if resp.code == "200"
    open(cacert_file, "wb") { |file| file.write(resp.body) }
    puts "\nTo complete this step, "
    puts "set the SSL_CERT_FILE system variable to <ssl_certs directory>\PerfectoMobileCA.pem"
    puts "go to Control Panel > Advanced > Environment Variables"
  else
    abort "\n\n>>>> A cacert.pem bundle could not be downloaded."
  end
end
Installation
============
### Prerequisites ###
You need to have Ruby, Devkit, and Android SDK installed.

#### Ruby ####

**For MAC:**
</br>Ruby is pre-installed.

**For windows:**


1. Download & Install the latest x64 Ruby version. You can get Ruby from [RubyInstaller.org](http://rubyinstaller.org/downloads/).
</br>Make sure to *uncheck* the "Add Ruby Executables to your Path" during the installation in order to avoid mistakes in step #2.
2.	Create a new **RUBY_HOME** system variable & Update the **PATH** System variable
	- Add **RUBY_HOME** system variable, with value pointing to the directory where it has been installed
	- Add **%RUBY_HOME%\bin** to the PATH system variable
3. Test the Ruby installation and system variables
	- In command line, run `ruby –v`. It should print "ruby 2.1.5" (or higher).

#### Devkit ####
Download & Install DevKit from [here](http://rubyinstaller.org/downloads/).

**To install the DevKit:**

1. Click the file and select where to extract it to – select a permanent location without whitespace e.g. C:\Devkit
2. Open command line and navigate to the Devkit folder.
3.	Run `ruby dk.rb init` and then ruby `dk.rb install`.
4.	Complete information can be [here](https://github.com/oneclick/rubyinstaller/wiki/Development-Kit).
5.  Confirm your Ruby environment is correctly using the DevKit by running `gem install json --platform=ruby`.
</br>JSON should be installed correctly and you should see with native extensions in the screen messages.
</br>Then run `ruby -rubygems -e "require 'json'; puts JSON.load('[42]').inspect` to confirm that the json gem is working.

### Installation ####

1. Install *calabash-perfectomobile* by running `gem install calabash-perfectomobile`
2. Install the trust certificate 
	- Copy PerfectoMobileCA.pem and install_cert.rb files located in `<RUBY_HOME directory>\lib\ruby\gems\<rubyVersion>\gems\calabash-perfectomobile-<version>\documents`	(also [here](https://github.com/calabash/calabash-perfectomobile/lib/ruby/gems/2.1.0/gems/calabash-perfectomobile-0.1.0))
	- Save these files into your ssl\_certs directory and cd to it (the ssl-certs directory is inside your Ruby installation for example C:\Ruby22-x64\lib\ruby\2.2.0\rubygems\ssl_certs). 
	- Install the certificate by running `ruby install_cert.rb`
	- Create a new sysem variable **SSL\_CERT\_FILE** and set it to `<ssl_certs directory>\PerfectoMobileCA.pem`
	- Close the command line window. 
	
:grey_exclamation: Remember to update your existing ***feature*** folders from the ***calabash-perfectomobile*** gem installation ***feature-skeleton*** subfolder (for example, merge your existing ***env.rb*** file with the new one)

Upgrading
---------
1. Uninstall the *calabash-perfectomobile* gem by running <br/>
	`gem uninstall calabash-perfectomobile`
2. Install the new *calabash-perfectomobile* gem by running <br/>
	`gem install calabash-perfectomobile`

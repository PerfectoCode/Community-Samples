# Modified by Perfecto Mobile Ltd
# -*- encoding: utf-8 -*-
$:.push File.expand_path('../lib', __FILE__)
require 'calabash-perfectomobile/version'

                 
Gem::Specification.new do |s|
  s.name        = 'calabash-perfectomobile'
  s.version     = Calabash::PerfectoMobile::VERSION
  s.license     = 'LICENSE'
  s.platform    = Gem::Platform::RUBY
  s.authors     = ['Asaf Saar']
  s.email       = ['support@perfectomobile.com']
  s.homepage    = 'http://github.com/calabash'
  s.summary     = %q{Client for calabash-perfectomobile for automated functional testing on Android and iOS using Perfecto Mobile}
  s.description = %q{calabash-perfectomobile drives tests for native Android and iOS apps using Perfecto Mobile.}
    
  s.files = Dir['lib/**/*'] + Dir['bin/**/*']
  s.files += Dir['[A-Z]*'] + Dir['documents/**/*'] + Dir['features-skeleton/**/*']

  s.executables   = 'calabash-perfectomobile'
  s.require_paths = ['lib']

  s.add_dependency( 'cucumber', '<= 1.3.18' )
  s.add_dependency( 'json', '~> 1')
  s.add_dependency( 'retriable', '~> 1.3', '>= 1.3.3.1' )
  s.add_dependency( 'slowhandcuke', '~> 0' )
  s.add_dependency( 'awesome_print', '~> 0' )
  s.add_dependency( 'httpclient', '~> 2.3', '>= 2.3.2')
  s.add_dependency( 'escape', '~> 0.0', '>= 0.0.4')
end

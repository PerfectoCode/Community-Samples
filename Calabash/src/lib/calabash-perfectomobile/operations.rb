# Modified by Perfecto Mobile Ltd.

require 'httpclient'
require 'retriable'
require 'cucumber'

require 'calabash-perfectomobile/perfectomobile_actions'
require 'calabash-perfectomobile/perfectomobile_helper'
require 'calabash-perfectomobile/version'

module Calabash
  module PerfectoMobile
    module Operations
      
      def macro(txt)
        if self.respond_to?(:step)
          step(txt)
        else
          Then(txt)
        end
      end
    
    end
  end
end


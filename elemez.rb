module Elemez

  class API
    
    @@sender = "Unknown"

    def self.instrument(sender)
      RhoLog::info('Elemez::API::instrument', 'instrumenting ' + sender)
      @@sender = sender
    end

    def self.sender  
      @@sender  
    end  

  end

end

module Rho

  class RhoError

    # patched methods
    alias_method :orig_initialize, :initialize

    def initialize(err_code)
      RhoLog::info("Rho::RhoError::initialize (elemez)", "raising disruption for error")

      source = ""
      userInitiated = 0

      case err_code
      when ERR_NETWORK
        source = "Network"
      when ERR_REMOTESERVER
        source = "Remote Server"
      when ERR_RUNTIME
        source = "Runtime"
      when ERR_UNEXPECTEDSERVERRESPONSE
        source = "Unexpected Response"
      when ERR_DIFFDOMAINSINSYNCSRC
        source = "Sync Source"
      when ERR_NOSERVERRESPONSE
        source = "No Response"
      when ERR_CLIENTISNOTLOGGEDIN
        source = "Not Logged In"
      when ERR_CUSTOMSYNCSERVER
        source = "Sync Server"
      when ERR_UNATHORIZED
        source = "Unauthorized"
      when ERR_CANCELBYUSER
        source = "Cancelled"
        userInitiated = 1
      when ERR_SYNCVERSION
        source = "Sync Version"
      when ERR_GEOLOCATION
        source = "Geolocation"
      else
        source = "Unknown"
      end

      Elemez::native_raiseDisruption(::Elemez::API::sender, source, userInitiated)
     
      orig_initialize(err_code)
    end

  end
end
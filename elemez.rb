module Elemez

  class API
    
    @@sender = "Unknown"

    def self.instrument(sender)
      RhoLog::info('Elemez::API', 'instrumenting ' + sender)
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
      RhoLog::info("elemez::RhoError::initialize", ::Rho::RhoError.err_message(err_code))

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

      timestamp = (Time.now.to_f * 1000).to_i
      RhoLog::info("elemez::RhoError::initialize - timestamp", timestamp)
      RhoLog::info("elemez::RhoError::initialize - sender", ::Elemez::API::sender)

      Elemez::native_raiseDisruption(42, ::Elemez::API::sender, source, userInitiated)

      # elemez_sessionBegin
      orig_initialize(err_code)
    end

  end
end
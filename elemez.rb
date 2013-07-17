module Elemez

  class API

    def self.instrument
      # just here to initiate monkey patching
      RhoLog::info('Elemez::API', 'instrument')
    end

  end

end

module Rho

  class RhoError

    # patched methods
    alias_method :orig_initialize, :initialize

    def initialize(err_code)
      RhoLog::info("elemez::RhoError::initialize", ::Rho::RhoError.err_message(err_code))
      Elemez::native_raiseDisruption(42, "A", "B", 0)
      # elemez_sessionBegin
      orig_initialize(err_code)
    end

  end
end
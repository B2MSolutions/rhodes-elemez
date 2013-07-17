# rhodes-elemez

## Introduction
[Rhodes](http://www.motorola.com/Business/US-EN/RhoMobile+Suite/Rhodes) is an open source Ruby-based framework to rapidly build native apps for all major smartphone operating systems (iPhone, Android, RIM, Windows Mobile and Windows Phone 7).

[rhodes-elemez](https://github.com/B2MSolutions/rhodes-elemez) is an extension that auto-instruments Rhodes applications for elemez. It requires the elemez client to be installed on the device. If the elemez client is unavailable the instrumentation will have no effect to the running of the application.

## Installation
[rhodes-elemez](https://github.com/B2MSolutions/rhodes-elemez) is a standard native extension for Rhodes and should be installed in the extensions folder of your application. 

Start a terminal window and cd to the root directory of your Rhodes application, then perform the following:

	> mkdir extensions
	> cd extensions
	> git clone git://github.com/B2MSolutions/rhodes-elemez.git elemez	

Now add the configuration to auto-instrument your application:
In *build.yaml* add the following:

```ruby	
extensions: ["elemez"]
```

Ensure your main application ruby file (the one that inherits from Rho::RhoApplication and is normally in app/application.rb) has the call to *Elemez::API::instrument* after the call to *super* as follows:

```ruby
require 'rho/rhoapplication'
require 'elemez'

class AppApplication < Rho::RhoApplication
  
  def initialize
    # Initialization before call to super
    super
	Elemez::API::instrument

    # Initialization after call to super...
  end	  
end
```

## Examples
We have forked the [Rhostore](https://github.com/B2MSolutions/rhostore-elemez) sample application refered to in the [rhomobile documentation](http://docs.rhomobile.com/), and configured it to use rhodes-elemez. It is available [here](https://github.com/B2MSolutions/rhostore-elemez).
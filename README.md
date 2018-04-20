# Winium Sample

A Web application using Clojure and ClojureScript for test the winium Server, in this case only send a command to the Windows cmd.

## Getting Started

### Prerequisites

* [Leiningen](https://leiningen.org/) - For automating Clojure projects without setting your hair on fire.
* [Winium](https://github.com/2gis/Winium) - Automation framework for Windows platforms.
	* Requirements:
		* Microsoft .NET Framework 4.5.1
	* Steps to setup the environment:
		* First we need to set up .NET Framework 4.5.1 in our system.
		* Then download the [winium.desktop.exe](https://github.com/2gis/Winium.Desktop/releases).
		* Start the server only by clicking in the icon, you will see something like this.
![Winium Server](https://github.com/andresr22/winium-sample-clojure/raw/master/assets/winiumStart.png)


### Dependencies and Plugins

All other libraries we are going to download through lein, below i am giving my lein dependencies and plugins

##### Interface dependecies and plugins

```clojure
:dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/core.async  "0.4.474"]
                 [reagent "0.7.0"]
                 [com.yetanalytics/re-mdl "0.1.8" :exclusions [cljsjs/react-with-addons]]
                 [cljsjs/react-with-addons "15.5.4-0"]
                 ; Http client dependencies
                 [cljs-http/cljs-http "0.1.45"]]

:plugins [[lein-figwheel "0.5.15"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]
```

* [Reagent](https://github.com/reagent-project/reagent) - Reagent provides a way to write efficient React components using (almost) nothing but plain ClojureScript functions.
* [Re-mdl](https://github.com/yetanalytics/re-mdl) - Yet another library of reusable UI components for Reagent.
* [Figwheel](https://github.com/bhauman/lein-figwheel) - Figwheel builds your ClojureScript code and hot loads it into the browser as you are coding!
* [Cljsbuild](https://github.com/emezeske/lein-cljsbuild) - This is a Leiningen plugin that makes it quick and easy to automatically compile your ClojureScript code into Javascript whenever you modify it.
* [cljs-http](https://github.com/r0man/cljs-http) -A HTTP library for ClojureScript.

##### Server dependecies and plugins

```clojure
:dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.3.1"]
                 ;[ring-cors/ring-cors "0.1.12"]
                 [jumblerg/ring.middleware.cors "1.0.1"]
                 ;Winium
                 [org.seleniumhq.selenium/selenium-java "2.53.0"]
                 [com.github.2gis.winium/winium-elements-desktop "0.2.0-1"]
                 [com.github.2gis.winium/winium-webdriver "0.1.0-1"]
                 [org.clojure/data.codec "0.1.1"]]

:plugins [[lein-ring "0.9.7"]
          [lein-cloverage "1.0.10"]]
```

* [Jumple/Ring](https://github.com/jumblerg/ring.middleware.cors) - Simple ring middleware for easy cross-origin resource sharing.
* [Winium Element Desktop](https://github.com/2gis/Winium.Elements) - This is a set of extensions for WebDriver Clients Bindings providing easy-to-use way of interacting with desktop-specific UI elements in Windows Desktop apps tested with Winium.Desktop.
* [Winium Webdriver](https://github.com/2gis/Winium/tree/master/java) - This is an extension of WebDriver Java bindings.

### Installing

* Clone the git repository

    `
    git clone https://github.com/andresr22/winium-sample-clojure.git
    `
    `
    cd winium-sample-clojure
    `

* Start the ring server

    `
    cd server
    `
    `
    lein ring server
    `

	If the server start correctly you will see something like this
    ![Ring Server](https://github.com/andresr22/winium-sample-clojure/raw/master/assets/ringServer.png)


## Running the tests

##### For test this app

You can write reloadable code, figwheel can facilitate automated live interactive programming.

Go to the app directory and run the figwheel project
`
cd winiunapp
`
`
lein figwheel
`
Automatically the browser open a new tab in `http://localhost:3449/index.html`
![Winium Interface](https://github.com/andresr22/winium-sample-clojure/raw/master/assets/interfface.png)

* __IP Server__.- is the ip from the winium server.
* __Command__.- the command you will send to the Windows cmd.
* __Screenshoot__.- if you want a screenshot for the test.






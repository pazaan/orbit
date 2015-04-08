---
layout: default
title: Orbit Framework
---
{% include JB/setup %}

![Orbit Framework](tpl/orbit-logo-black.png)

 [![Build Status](https://travis-ci.org/electronicarts/orbit.svg?branch=master)](https://travis-ci.org/electronicarts/orbit)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.ea.orbit/orbit-parent/badge.svg)](https://repo1.maven.org/maven2/com/ea/orbit/)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/electronicarts/orbit?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

Orbit is a modern framework for JVM languages that makes it easier to build and maintain distributed and scalable online services.

Orbit contains two primary components: 

-  Orbit Actors, a framework to write distributed systems using virtual actors.
-  Orbit Container, a minimal inversion of control container for building online services.

It was developed by [BioWare](http://www.bioware.com), a division of [Electronic Arts](http://www.ea.com). For the latest news, follow us on [Twitter](https://twitter.com/OrbitFramework). 
<br /> If you're looking for virtual actors on the .NET CLR, see [Orleans](https://github.com/dotnet/Orleans). 

You can grab the code on [GitHub](https://github.com/electronicarts/orbit) or [get started](http://orbit.bioware.com/orbit-getting-started.html) now.

 <br/>

**Actors - Java Example** 
{% highlight java %}
public interface IHello extends IActor
{
    Task<String> sayHello(String greeting);
}
 
public class HelloActor extends OrbitActor implements IHello
{
    public Task<String> sayHello(String greeting)
    {
        getLogger().info("Here: " + greeting);
        return Task.fromValue("Hello There");
    }
}
 
IActor.getReference(IHello.class, "0").sayHello("Meep Meep");
{% endhighlight %}

**Actors - Scala Example** 
{% highlight scala %}
trait IHello extends IActor {
  def sayHello(greeting: String): Task[String]
}

class HelloActor extends OrbitActor[AnyRef] with IHello {
  def sayHello(greeting: String): Task[String] = {
    getLogger.info("Here: " + greeting)
    Task.fromValue("Hello There")
  }
}

IActor.getReference(classOf[IHello], "0").sayHello("Meep Meep")
{% endhighlight %}
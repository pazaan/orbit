/*
 Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1.  Redistributions of source code must retain the above copyright
     notice, this list of conditions and the following disclaimer.
 2.  Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
 3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
     its contributors may be used to endorse or promote products derived
     from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.ea.orbit.async.instrumentation;

/**
 * Internal class to (when necessary) attach a java agent to
 * the jvm to instrument async-await methods.
 */
public class InitializeAsync
{
    /**
     * Name of the property that will be set by the
     * Agent to flag that the instrumentation is already running.
     *
     * There are two definitions of this constant because the initialization
     * classes are not supposed be accessed by the agent classes, and vice-versa
     *
     * @see com.ea.orbit.async.instrumentation.InitializeAsync#ORBIT_ASYNC_RUNNING
     * @see com.ea.orbit.async.instrumentation.Transformer#ORBIT_ASYNC_RUNNING
     */
    static final String ORBIT_ASYNC_RUNNING = "orbit-async.running";

    static
    {
        if (!"true".equals(System.getProperty(InitializeAsync.ORBIT_ASYNC_RUNNING, "false")))
        {
            // the indirection is necessary to prevent
            // touching "com.sun.tools" when the agent was loaded via
            // the jvm parameter "-javaagent"
            try
            {
                Class.forName("com.ea.orbit.async.instrumentation.AgentLoader").newInstance();
            }
            catch (Exception e)
            {
                throw new RuntimeException("Error attaching orbit-async java agent", e);
            }
        }
    }

    public static void init()
    {
        // does nothing but causes the static initialization to run.
        // static initialization runs only once and it is synchronized.
    }
}

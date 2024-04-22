Online Messaging Application

Description:
The Online Messaging Application allows users to send and receive messages concurrently.
This application has two versions: a thread-unsafe version (`MessageServiceUnsafe`) and
a thread-safe version (`SafeMessageService`). The thread-unsafe version experiences race conditions
when multiple threads attempt to modify the shared message data structure concurrently,
while the thread-safe version ensures proper synchronization to prevent race conditions.

Race Condition Scenario:
In the thread-unsafe version (`MessageServiceUnsafe`), a race condition occurs when multiple threads attempt
to add messages to the shared `messages` list concurrently. This leads to duplicated messages
and inconsistent ordering of messages in the output.

Revised Version:
To address the race condition, the thread-safe version (`SafeMessageService`) introduces a `ReentrantLock`
to synchronize access to the shared `messages` list. With proper synchronization, only one thread
can modify the list at a time, ensuring that messages are added in a consistent and thread-safe manner.

Comparison:
Testing both versions of the Online Messaging Application results in the following outputs:

Testing thread-unsafe version:
Messages (Thread-Unsafe): [List of duplicated and inconsistently ordered messages]

Testing thread-safe version:
Messages (Thread-Safe): [List of non-duplicated and consistently ordered messages]

Conclusion:
The comparison between the thread-unsafe and thread-safe versions highlights the importance of
thread safety in concurrent applications. Proper synchronization mechanisms, such as using locks,
are crucial to prevent race conditions and ensure the reliability and consistency of message delivery
in multi-threaded environments.

package com.manfred.parseq.study;

import com.linkedin.parseq.promise.Promise;
import com.linkedin.parseq.promise.Promises;
import com.linkedin.parseq.promise.SettablePromise;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author Chris Pettitt (cpettitt@linkedin.com)
 */
public class MockService<RES> {
  protected final ScheduledExecutorService _scheduler;

  public MockService(ScheduledExecutorService scheduler) {
    _scheduler = scheduler;
  }

  public Promise<RES> call(final MockRequest<RES> request) {
    final SettablePromise<RES> promise = Promises.settable();
    _scheduler.schedule(new Runnable() {
      @Override
      public void run() {
        try {
          promise.done(request.getResult());
        } catch (Exception e) {
          promise.fail(e);
        }
      }
    }, request.getLatency(), TimeUnit.MILLISECONDS);
    return promise;
  }
}
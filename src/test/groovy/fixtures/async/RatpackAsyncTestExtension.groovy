package fixtures.async

import ratpack.exec.Operation
import ratpack.exec.Promise
import ratpack.test.exec.ExecHarness
import spock.lang.Specification

// from: https://gist.github.com/alkemist/7d001fb9a8c894c982e7

final class RatpackAsyncTestExtension {

    private RatpackAsyncTestExtension() {
    }

    /**
     * Synchronously executes the given promise and returns the promised value, or throws the exception if it's an error.
     * <p>
     * The calling thread will block, waiting for the promised value to be provided.
     *
     * @param self this promise
     * @return the result of the execution
     */
    static <T> T yield(Promise<T> self) {
        ExecHarness.yieldSingle { self }.valueOrThrow
    }

    /**
     * Synchronously executes the given operation, or throws the exception if it's an error.
     * <p>
     * The calling thread will block, waiting for the operation to finish execution.
     *
     * @param self this operation
     */
    static void await(Operation operation) {
        ExecHarness.yieldSingle { operation.promise() }.valueOrThrow
    }

    /**
     * Executes the given runnable within a Ratpack execution as an operation and blocks until it completes.
     * <p>
     * This extension method is targeted at {@link Specification} to make it available in test cases.
     *
     * @param self the test case (not used)
     * @param runnable the action to invoke within an execution
     */
    @SuppressWarnings("GroovyUnusedDeclaration")
    static void exec(Specification self, Runnable runnable) {
        Operation.of { runnable.run() }.await()
    }
}

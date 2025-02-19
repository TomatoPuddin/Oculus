package io.github.douira.glsl_transformer.token_filter;

import java.util.function.Supplier;

import repack.antlr.v4.runtime.Token;

import io.github.douira.glsl_transformer.ast.transform.JobParameters;

/**
 * A token filter is an object that can check if given tokens should be printed
 * or not.
 */
public abstract class TokenFilter<J extends JobParameters> {
  private Supplier<J> jobParametersSupplier;

  /**
   * Checks if the token should be printed.
   * 
   * @param token The token to check
   * @return {@code true} if the given token should be printed
   */
  public abstract boolean isTokenAllowed(Token token);

  /**
   * Resets the filter's state. Does nothing by default.
   */
  public void resetState() {
  }

  /**
   * Sets the job parameters supplier.
   * 
   * @param jobParametersSupplier The job parameters supplier
   */
  public void setJobParametersSupplier(Supplier<J> jobParametersSupplier) {
    this.jobParametersSupplier = jobParametersSupplier;
  }

  /**
   * Gets the job parameters.
   * 
   * @return The job parameters
   */
  protected J getJobParameters() {
    return jobParametersSupplier.get();
  }

  /**
   * Joins two arbitrary token filters into a new filter. They may be null,
   * regular filters or multi filters and will be joined accordingly. The returned
   * instance is either a or b or a new multi filter containing a, b, or their
   * contents.
   * 
   * If a multi filter is generated, the settings from the first multi filter in
   * the parameters are copied.
   * 
   * @param <J> The job parameter type
   * @param a   A token filter. May be {@code null}.
   * @param b   A token filter. May be {@code null}.
   * @return The joined token filter
   */
  public static <J extends JobParameters> TokenFilter<J> join(TokenFilter<J> a, TokenFilter<J> b) {
    if (a == null) {
      return b;
    } else if (b == null) {
      return a;
    } else if (MultiFilter.class.isInstance(b)) {
      if (MultiFilter.class.isInstance(a)) {
        MultiFilter<J> bMulti = (MultiFilter<J>) b;
        MultiFilter<J> aMulti = (MultiFilter<J>) a;
        var multi = aMulti.clone();
        multi.addAll(bMulti);
        return multi;
      } else {
        return join(b, a);
      }
    } else if (MultiFilter.class.isInstance(a)) {
      MultiFilter<J> aMulti = (MultiFilter<J>) a;
      var multi = aMulti.clone();
      multi.add(b);
      return multi;
    } else {
      var multi = new MultiFilter<J>();
      multi.add(a);
      multi.add(b);
      return multi;
    }
  }
}

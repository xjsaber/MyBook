

### 4.8.1 Enum classes ###
    private enum Answer {
      YES {
        @Override public String toString() {
          return "yes";
        }
      },
    
      NO,
      MAYBE
    }
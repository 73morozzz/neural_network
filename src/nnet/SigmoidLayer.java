package nnet;

/**
 * ������������� ����
 */
public final class SigmoidLayer implements BackpropLayer {
    /**
     * ���
     */
    private final int WEIGHT = 0;

    /**
     * ������
     */
    private final int DELTA = 1;
    
    /**
     * ������ �������� �������
     */
    private final int inputSize;

    /**
     * ���� ����������� ����
     */
    private final boolean bipolar;

    /**
     * ������� ����
     */
    private float[][][] matrix;
    
    /**
     * ��� ����
     */
    public enum TypeLayer { SIGMOID, LINEAR, TG, DOORSTEP }
    private TypeLayer type; 

    /**
     * ����������� ������������� ����
     * @param inputSize ������ �������� �������
     * @param size ������ ����
     * @param bipolar ���� ����������� ����
     */
    public SigmoidLayer(int inputSize, int size, boolean bipolar, TypeLayer type) {
    	this.type = type;
    	
        // ��������
        if (inputSize < 1 || size < 1) throw new IllegalArgumentException();

        // ������� ����
        matrix = new float[size][inputSize + 1][2];

        // �������� ���������
        this.inputSize = inputSize;
        this.bipolar = bipolar;
    }

    /**
     * ������������ ���������� ����
     * @param inputSize ������ �������� �������
     * @param size ������ ����
     */
    public SigmoidLayer(int inputSize,int size, TypeLayer type) {
        this(inputSize,size,true, type);
    }

    public int getInputSize() {
        return inputSize;
    }

    public int getSize() {
        return matrix.length;
    }
    
    public TypeLayer getType() {
        return type;
    }
    
    public float[][][] getMatrix() {
        return matrix;
    }

    public float[] computeOutput(float[] input) {
        // ��������
        if (input == null)// || input.length != inputSize)
                throw new IllegalArgumentException();

        // �������� �����
        final int size = matrix.length;
        float[] output = new float[size];
        for (int i = 0; i < size; i++) {
            output[i] = matrix[i][0][WEIGHT];
            for (int j = 0; j < inputSize; j++)
                output[i] += input[j] * matrix[i][j + 1][WEIGHT];
            if (bipolar)
                output[i] = (float)Math.tanh(output[i]);
            else
                output[i] = 1 / (1 + (float)Math.exp(-output[i]));
        }

        // ������ �����
        return output;
    }

    public void randomize(float min,float max) {
        final int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < inputSize + 1; j++) {
                matrix[i][j][WEIGHT] = min + (max - min) * (float)Math.random();
                matrix[i][j][DELTA] = 0;
                //System.out.println(matrix[i][j][WEIGHT] + " " + matrix[i][j][DELTA]);
            }
        }
    }

    public float[] computeBackwardError(float[] input,float[] error) {
        // ��������
        if (input == null || input.length != inputSize ||
                error == null || error.length != matrix.length) throw new IllegalArgumentException();

        // �������� �������� ������
        float[] output = computeOutput(input);
        final int size = matrix.length;
        float[] backwardError = new float[inputSize];

        for (int i = 0; i < inputSize; i++) {
            backwardError[i] = 0;
            for (int j = 0; j < size; j++)
                backwardError[i] += error[j] * matrix[j][i + 1][WEIGHT] *
                        (bipolar ? 1 - output[j] * output[j] : output[j] * (1 - output[j]));
        }

        // ������ ������
        return backwardError;
    }

    public void adjust(float[] input,float[] error,float rate,float momentum) {
        // ��������
        if (input == null || //input.length != inputSize || 
                error == null || error.length != matrix.length) 
        	throw new IllegalArgumentException();

        // ��������� ����
        float[] output = computeOutput(input);
        final int size = matrix.length;

        for (int i = 0; i < size; i++) {
            final float grad = error[i] * (bipolar ? 1 - output[i] * output[i] : output[i] * (1 - output[i]));
            // ��������� ������� ���
            matrix[i][0][DELTA] = rate * grad + momentum * matrix[i][0][DELTA];
            matrix[i][0][WEIGHT] += matrix[i][0][DELTA];
            // ������� ��������� ����
            for (int j = 0; j < inputSize; j++) {
                matrix[i][j + 1][DELTA] = rate * input[j] * grad + momentum * matrix[i][j + 1][DELTA];
                matrix[i][j + 1][WEIGHT] += matrix[i][j + 1][DELTA];
            }
        }
    }

    
}
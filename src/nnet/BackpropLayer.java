package nnet;

/**
 * ��������� ���� ���������� �� ��������� ��������� ��������������� ������
 */
public interface BackpropLayer extends Layer {
    /**
     * ������� ��������� �������� ����� ��������
     * @param min ����������� ��������
     * @param max ������������ ��������
     */
    void randomize(float min,float max);

    /**
     * ���������� ��������� ������ ������ � �������� �����������
     * @param input ������� ������
     * @param error ������ ������
     * @return ��������� ������ ������ � �������� �����������
     */
    float[] computeBackwardError(float[] input,float[] error);

    /**
     * ��������� ���� �������� � ������� ���������� ������
     * @param input ������� ������
     * @param error ������ ������
     * @param rate �������� ��������
     * @param momentum ��������
     */
    void adjust(float[] input,float[] error,float rate,float momentum);
}